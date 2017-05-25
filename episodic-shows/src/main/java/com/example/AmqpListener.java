package com.example;

/**
 * Created by trainer9 on 5/23/17.
 */


import com.example.episodes.Episode;
import com.example.episodes.EpisodeRepository;
import com.example.viewings.Progress;
import com.example.viewings.Viewing;
import com.example.viewings.ViewingRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import javax.transaction.Transactional;

/**
 * Created by trainer9 on 5/23/17.
 */
@Configuration
public class AmqpListener implements RabbitListenerConfigurer {


    private final ViewingRepository viewRepo;
    private final EpisodeRepository episodeRepo;


    public AmqpListener(ViewingRepository viewRepo, EpisodeRepository episodeRepo) {
        this.viewRepo = viewRepo;
        this.episodeRepo = episodeRepo;
    }


    @RabbitListener(queues = "episodic-progress")
    @Transactional
    public void receiveMessage(Progress progress) {

        Viewing returnedViewing = viewRepo.findViewingByEpisodeId(progress.getEpisodeId());

        if (null != returnedViewing) {
            viewRepo.updateViewingEpisodeTimeCodebyUserId(progress.getEpisodeId(), progress.getCreatedAt(),
                    progress.getOffset(), progress.getUserId());
        } else {
            Episode returnedEpisode =  episodeRepo.findByEpisodeNumber(Math.toIntExact(progress.getEpisodeId()));
            Viewing viewing = new Viewing();
            viewing.setUpdatedAt(progress.getCreatedAt());
            viewing.setTimecode(progress.getOffset());
            viewing.setEpisodeId(progress.getEpisodeId());
            if(null!=returnedEpisode) {
                viewing.setShowId(returnedEpisode.getShowId());
            }else
            {
                viewing.setShowId(Long.parseLong("1"));
            }
            viewing.setUserId(progress.getUserId());
            System.out.println("************************************************");
            System.out.println(progress.toString());
            System.out.println("************************************************");
            viewRepo.save(viewing);
        }

        }

        @Bean
        public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory () { // <-- 2
            DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
            factory.setMessageConverter(new MappingJackson2MessageConverter());
            return factory;
        }

        @Override
        public void configureRabbitListeners ( final RabbitListenerEndpointRegistrar registrar){  // <-- 3
            registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
        }
    }
