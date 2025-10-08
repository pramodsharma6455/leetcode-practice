select  e.name as Employee from employee e
join employee m
 ON e.managerId = m.id
WHERE e.salary > m.salary;


