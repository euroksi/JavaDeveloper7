WITH ProjectDurations AS (
    SELECT
        ID,
        CLIENT_ID,
        START_DATE,
        FINISH_DATE,
        EXTRACT(MONTH FROM (FINISH_DATE - START_DATE)) + 1 AS DURATION_MONTHS
    FROM
        project
    WHERE
        FINISH_DATE IS NOT NULL
),
MaxDuration AS (
    SELECT
        MAX(DURATION_MONTHS) AS MAX_DURATION
    FROM
        ProjectDurations
)
SELECT
    p.ID,
    p.CLIENT_ID,
    p.START_DATE,
    p.FINISH_DATE,
    pd.DURATION_MONTHS
FROM
    ProjectDurations pd
JOIN
    MaxDuration md
ON
    pd.DURATION_MONTHS = md.MAX_DURATION
JOIN
    project p
ON
    pd.ID = p.ID;