import os
import re
from pathlib import Path
from datetime import date

JAVA_SRC_DIR = Path('src/main/java')
RESOURCES_DIR = Path('src/main/resources')

TYPE_MAPPING = {
    'String': 'VARCHAR(255)',
    'Long': 'BIGINT',
    'long': 'BIGINT',
    'Integer': 'INT',
    'int': 'INT',
    'Double': 'DOUBLE',
    'double': 'DOUBLE',
    'Float': 'FLOAT',
    'float': 'FLOAT',
    'Boolean': 'BOOLEAN',
    'boolean': 'BOOLEAN',
    'LocalDate': 'DATE',
    'LocalDateTime': 'TIMESTAMP',
    'UUID': 'UUID',
}

def main():
    today = date.today().isoformat()
    output_file = RESOURCES_DIR / f"{today}.sql"
    sql_sections = ["-- Auto-generated SQL schema"]

    for root, _, files in os.walk(JAVA_SRC_DIR):
        for filename in files:
            if not filename.endswith('.java'):
                continue
            path = Path(root) / filename
            text = path.read_text()
            if '@Table' not in text:
                continue
            table_match = re.search(r'@Table\((?:name=)?"([^"\)]+)"\)', text)
            if not table_match:
                continue
            table_name = table_match.group(1)
            columns = []
            id_next = False
            for line in text.splitlines():
                line = line.strip()
                if line.startswith('@Id'):
                    id_next = True
                    continue
                field_match = re.match(r'(public|private|protected)\s+([\w<>]+)\s+(\w+);', line)
                if field_match:
                    java_type = field_match.group(2)
                    field_name = field_match.group(3)
                    sql_type = TYPE_MAPPING.get(java_type, 'TEXT')
                    column = f"    {field_name} {sql_type}"
                    if id_next:
                        column += ' PRIMARY KEY'
                        id_next = False
                    columns.append(column)
            if not columns:
                continue
            section = [f"CREATE TABLE {table_name} (", ",\n".join(columns), ");", ""]
            sql_sections.extend(section)

    RESOURCES_DIR.mkdir(parents=True, exist_ok=True)
    with open(output_file, 'w') as f:
        f.write("\n".join(sql_sections))
    print(f"Generated {output_file}")

if __name__ == '__main__':
    main()
