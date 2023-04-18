Klassenübersicht:

Name: TASK
Attribute: (String) Title, (Tag) Tags, (String) Description, (Date) Deadline, (Class / Enum) Status, (Class / Enum) Priority, (Boolean) isClarified
Methoden:
Konstruktor nur mit Titel, rest optional
update Methode für jedes Attribut



Name: Tag
Attribute: (String) Title



Name: Project
Attribute: (Tasks) Tasks, (String) Title, (Date) Deadline
Methoden:
Konstruktor: title, deadline optional, keine tasks
addTask
removeTask
getTaskByTitle
getTasksByTagName
updateTask
