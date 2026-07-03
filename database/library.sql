-- ============================================================================
-- Project : Library Management System
-- Author  : Amrit Chandan Mishra
-- Version : 1.0.0
-- Database: MySQL 8.x
-- ============================================================================

DROP DATABASE IF EXISTS library_db;

CREATE DATABASE library_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE library_db;

-- ============================================================================
-- TABLE : roles
-- ============================================================================

CREATE TABLE roles
(
    role_id INT AUTO_INCREMENT PRIMARY KEY,

    role_name VARCHAR(50) NOT NULL UNIQUE,

    description VARCHAR(255),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLE : categories
-- ============================================================================

CREATE TABLE categories
(
    category_id INT AUTO_INCREMENT PRIMARY KEY,

    category_name VARCHAR(100) NOT NULL UNIQUE,

    description VARCHAR(255),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLE : authors
-- ============================================================================

CREATE TABLE authors
(
    author_id INT AUTO_INCREMENT PRIMARY KEY,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    email VARCHAR(150),

    phone VARCHAR(20),

    country VARCHAR(100),

    biography TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLE : publishers
-- ============================================================================

CREATE TABLE publishers
(
    publisher_id INT AUTO_INCREMENT PRIMARY KEY,

    publisher_name VARCHAR(150) NOT NULL UNIQUE,

    email VARCHAR(150),

    phone VARCHAR(20),

    address VARCHAR(255),

    website VARCHAR(200),

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================================
-- Seed Data : Roles
-- ============================================================================

INSERT INTO roles (role_name, description)
VALUES
    ('ADMIN', 'System Administrator'),
    ('LIBRARIAN', 'Library Staff');

-- ============================================================================
-- Seed Data : Categories
-- ============================================================================

INSERT INTO categories (category_name, description)
VALUES
    ('Programming', 'Programming Books'),
    ('Database', 'Database Books'),
    ('Networking', 'Networking Books'),
    ('Artificial Intelligence', 'AI Books'),
    ('Cyber Security', 'Security Books'),
    ('Operating System', 'Operating System Books'),
    ('Web Development', 'Web Development Books'),
    ('Data Structures', 'Data Structures and Algorithms'),
    ('Software Engineering', 'Software Engineering Books'),
    ('Cloud Computing', 'Cloud Technology');


-- ============================================================================
-- TABLE : members
-- ============================================================================

CREATE TABLE members
(
    member_id INT AUTO_INCREMENT PRIMARY KEY,

    member_code VARCHAR(20) NOT NULL UNIQUE,

    first_name VARCHAR(100) NOT NULL,

    last_name VARCHAR(100) NOT NULL,

    gender ENUM('MALE','FEMALE','OTHER'),

    email VARCHAR(150) UNIQUE,

    phone VARCHAR(20),

    address VARCHAR(255),

    date_of_birth DATE,

    join_date DATE NOT NULL,

    membership_status ENUM
        (
        'ACTIVE',
        'INACTIVE',
        'BLOCKED'
    ) DEFAULT 'ACTIVE',

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP
);

-- ============================================================================
-- TABLE : books
-- ============================================================================

CREATE TABLE books
(
    book_id INT AUTO_INCREMENT PRIMARY KEY,

    category_id INT NOT NULL,

    author_id INT NOT NULL,

    publisher_id INT NOT NULL,

    isbn VARCHAR(20) NOT NULL UNIQUE,

    title VARCHAR(250) NOT NULL,

    edition VARCHAR(50),

    language VARCHAR(50),

    publish_year YEAR,

    total_pages INT,

    description TEXT,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
        ON UPDATE CURRENT_TIMESTAMP,

    CONSTRAINT fk_books_category
        FOREIGN KEY(category_id)
            REFERENCES categories(category_id)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,

    CONSTRAINT fk_books_author
        FOREIGN KEY(author_id)
            REFERENCES authors(author_id)
            ON UPDATE CASCADE
            ON DELETE RESTRICT,

    CONSTRAINT fk_books_publisher
        FOREIGN KEY(publisher_id)
            REFERENCES publishers(publisher_id)
            ON UPDATE CASCADE
            ON DELETE RESTRICT
);

-- ============================================================================
-- TABLE : book_copies
-- ============================================================================

CREATE TABLE book_copies
(
    copy_id INT AUTO_INCREMENT PRIMARY KEY,

    book_id INT NOT NULL,

    barcode VARCHAR(50) NOT NULL UNIQUE,

    shelf_location VARCHAR(100),

    status ENUM
        (
        'AVAILABLE',
        'ISSUED',
        'RESERVED',
        'LOST',
        'DAMAGED'
    ) DEFAULT 'AVAILABLE',

    purchase_date DATE,

    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_copy_book
        FOREIGN KEY(book_id)
            REFERENCES books(book_id)
            ON UPDATE CASCADE
            ON DELETE CASCADE
);