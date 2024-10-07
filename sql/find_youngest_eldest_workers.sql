WITH Youngest AS (
    SELECT
        'YOUNGEST' AS TYPE,
        NAME,
        BIRTHDAY
    FROM worker
    WHERE BIRTHDAY = (SELECT MIN(BIRTHDAY) FROM worker)
),
Oldest AS (
    SELECT
        'OLDEST' AS TYPE,
        NAME,
        BIRTHDAY
    FROM worker
    WHERE BIRTHDAY = (SELECT MAX(BIRTHDAY) FROM worker)
)
-- Виведення результатів
SELECT * FROM Youngest
UNION ALL
SELECT * FROM Oldest;
