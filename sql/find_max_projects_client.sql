WITH ClientProjectCounts AS (
    SELECT
        CLIENT_ID,
        COUNT(*) AS PROJECT_COUNT
    FROM
        project
    GROUP BY
        CLIENT_ID
),
MaxProjectCount AS (
    SELECT
        MAX(PROJECT_COUNT) AS MAX_COUNT
    FROM
        ClientProjectCounts
)
SELECT
    c.ID,
    c.NAME,
    cpc.PROJECT_COUNT
FROM
    ClientProjectCounts cpc
JOIN
    MaxProjectCount mpc
ON
    cpc.PROJECT_COUNT = mpc.MAX_COUNT
JOIN
    client c
ON
   cpc.CLIENT_ID = c.ID;