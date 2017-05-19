create table viewings (
  id bigint not null auto_increment primary key,
  user_id bigint not NULL,
  show_id bigint not NULL,
  episode_id bigint not NULL,
  updated_at DATETIME NOT NULL,
  timecode int not null,

  FOREIGN KEY (show_id)
        REFERENCES shows(id)
        ON DELETE CASCADE,
  FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,
  FOREIGN KEY (episode_id)
        REFERENCES episodes(id)
        ON DELETE CASCADE

);