WITH ProjectDurations AS (
    SELECT
        p.ID AS PROJECT_ID,
        p.NAME AS PROJECT_NAME,
        EXTRACT(YEAR FROM (p.FINISH_DATE - p.START_DATE)) * 12 + EXTRACT(MONTH FROM (p.FINISH_DATE - p.START_DATE)) AS DURATION_MONTHS
    FROM project p
),
ProjectSalaries AS (
    SELECT
        pw.PROJECT_ID,
        SUM(w.SALARY) AS TOTAL_SALARY
    FROM project_worker pw
    JOIN worker w ON pw.WORKER_ID = w.ID
    GROUP BY pw.PROJECT_ID
),
ProjectCosts AS (
    SELECT
        pd.PROJECT_NAME AS NAME,
        (ps.TOTAL_SALARY * pd.DURATION_MONTHS) AS PRICE
    FROM ProjectDurations pd
    JOIN ProjectSalaries ps ON pd.PROJECT_ID = ps.PROJECT_ID
)
SELECT
    NAME,
    PRICE
FROM ProjectCosts
ORDER BY PRICE DESC;
