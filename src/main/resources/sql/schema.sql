CREATE DATABASE IF NOT EXISTS video_ls DEFAULT CHARACTER SET utf8mb4;

USE video_ls;

CREATE TABLE IF NOT EXISTS user (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(128) NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS admin_user (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(128) NOT NULL,
    created_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_admin_username (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS course_category (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(128) NOT NULL,
    sort_order INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS course (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    category_id BIGINT UNSIGNED NOT NULL,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    cover_url VARCHAR(255),
    status TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (id),
    KEY idx_course_category (category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS video (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    course_id BIGINT UNSIGNED NOT NULL,
    title VARCHAR(255) NOT NULL,
    play_url VARCHAR(255) NOT NULL,
    duration_seconds INT NOT NULL DEFAULT 0,
    sort_order INT NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    KEY idx_video_course (course_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS learning_progress (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id BIGINT UNSIGNED NOT NULL,
    video_id BIGINT UNSIGNED NOT NULL,
    progress_seconds INT NOT NULL DEFAULT 0,
    updated_at DATETIME NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_progress_user_video (user_id, video_id),
    KEY idx_progress_user (user_id),
    KEY idx_progress_video (video_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
