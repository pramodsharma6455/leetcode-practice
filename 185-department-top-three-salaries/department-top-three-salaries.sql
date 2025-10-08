SELECT Department, Employee, salary AS Salary
FROM (
    SELECT d.name AS Department,
           e.name AS Employee,
           e.salary,
           DENSE_RANK() OVER (PARTITION BY d.name ORDER BY e.salary DESC) AS rnk
    FROM Employee e
    INNER JOIN Department d
        ON e.departmentId = d.id
) AS ranked
WHERE rnk <= 3
ORDER BY Department, salary DESC;

