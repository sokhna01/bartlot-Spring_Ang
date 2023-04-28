Les instructions pour executer le projet sont dans le fichier INSTRUCTION.docx

Pour intégrer une base de données dumpée avec pg_dump dans un fichier SQL, vous pouvez suivre les étapes suivantes:
    - Assurez-vous que le serveur de base de données est en cours d'execution
    - Ouvrir le terminal et naviguer vers le repertoire contenant le fichier bartlot.sql
    - Se connecter au serveur de base de données avec la commande: psql -d nom_de_la_base_de_données
    - Executer la commande : \i chemin/vers/fichier_dump.sql pour intégrer le fichier de la base de données dumpée dans votre base de données
    - Une fois la commande exécutée, la base de données dumpée sera intégrée dans la base de données PostgreSQL que vous avez spécifiée.

