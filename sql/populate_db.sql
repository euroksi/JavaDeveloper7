INSERT INTO worker (NAME, BIRTHDAY, LEVEL, SALARY) VALUES
('Alice Johnson', '1990-05-15', 'Trainee', 800),
('Bob Brown', '1985-08-22', 'Junior', 1200),
('Charlie Davis', '1980-02-10', 'Middle', 2500),
('David Wilson', '1975-11-30', 'Senior', 5500),
('Eve Taylor', '1992-07-07', 'Trainee', 950),
('Frank Moore', '1988-12-05', 'Junior', 1500),
('Grace Lee', '1983-03-12', 'Middle', 3200),
('Hannah White', '1978-09-25', 'Senior', 6000),
('Ian Clark', '1989-06-18', 'Trainee', 1100),
('Jack Harris', '1987-10-30', 'Senior', 6500);

INSERT INTO client (NAME) VALUES
('TechCorp'),
('Innovate Ltd'),
('FutureWorks'),
('Global Solutions'),
('Alpha Industries');

INSERT INTO project (CLIENT_ID, START_DATE, FINISH_DATE) VALUES
(1, '2023-01-10', '2023-07-10'),
(2, '2023-03-01', '2024-01-01'),
(3, '2022-11-15', '2023-09-15'),
(4, '2022-07-01', '2023-05-01'),
(5, '2023-05-20', '2024-05-20'),
(1, '2023-02-01', '2023-12-01'),
(2, '2022-08-15', '2023-04-15'),
(3, '2023-01-01', '2023-11-01'),
(5, '2022-12-15', '2023-09-15');

INSERT INTO project_worker (PROJECT_ID, WORKER_ID) VALUES
(1, 1), (1, 2), (1, 3),
(2, 4), (2, 5), (2, 6),
(3, 7), (3, 8),
(4, 1), (4, 2), (4, 4),
(5, 3), (5, 5), (5, 6), (5, 7),
(6, 8), (6, 9),
(7, 1), (7, 2), (7, 4),
(8, 3), (8, 5), (8, 6),
(9, 7), (9, 8),
(10, 1), (10, 2), (10, 3), (10, 4);