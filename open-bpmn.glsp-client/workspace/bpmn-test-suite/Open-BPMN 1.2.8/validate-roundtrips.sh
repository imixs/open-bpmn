#!/bin/bash

# BPMN Roundtrip Validation Script
# Validates all files ending with 'roundtrip.bpmn' against the BPMN 2.0 XSD schema

# Check if xmlstarlet is installed
if ! command -v xmlstarlet &> /dev/null; then
    echo "Error: xmlstarlet is not installed!"
    echo "Installation on Ubuntu/Debian: sudo apt-get install xmlstarlet"
    echo "Installation on macOS: brew install xmlstarlet"
    exit 1
fi

# Check if schema file exists
SCHEMA_FILE="schema/BPMN20.xsd"
if [ ! -f "$SCHEMA_FILE" ]; then
    echo "Error: Schema file '$SCHEMA_FILE' not found!"
    exit 1
fi

# Counters for statistics
total_files=0
valid_files=0
invalid_files=0

echo "Starting BPMN validation..."
echo "Schema: $SCHEMA_FILE"
echo "----------------------------------------"

# Find and validate all *roundtrip.bpmn files
while IFS= read -r -d '' file; do
    ((total_files++))
    echo -n "Validating: $(basename "$file") ... "
    
    if xmlstarlet validate -e --xsd "$SCHEMA_FILE" "$file" 2>/dev/null; then
        echo "✓ VALID"
        ((valid_files++))
    else
        echo "✗ INVALID"
        ((invalid_files++))
        # Show validation errors
        echo "  Error details:"
        xmlstarlet validate -e --xsd "$SCHEMA_FILE" "$file" 2>&1 | sed 's/^/    /'
        echo
    fi
done < <(find . -name "*roundtrip.bpmn" -type f -print0)

# Output statistics
echo "----------------------------------------"
echo "Validation completed!"
echo "Total files: $total_files"
echo "Valid files: $valid_files"
echo "Invalid files: $invalid_files"

# Exit code based on results
if [ $invalid_files -gt 0 ]; then
    exit 1
else
    exit 0
fi