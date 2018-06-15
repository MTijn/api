CREATE TABLE blog_post
(
    id UUID NOT NULL
      CONSTRAINT blog_post_pkey
      PRIMARY KEY,
    title VARCHAR(256) NOT NULL,
    content TEXT NOT NULL,
    tags VARCHAR(256) NOT NULL,
    author VARCHAR(256) NOT NULL,
    publishedAt TIMESTAMP,
    createdAt TIMESTAMP NOT NULL
)