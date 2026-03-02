# Employee Payroll System

A Java-based payroll management system that reads employee data, performs sorting algorithms, binary search, and generates sorted output files.

---

## Project Structure

```
DATASTRUCTURES-ASSIGMENT-2/
├── data/
│   ├── employeesWithoutRepeat.txt     ← main input file
│   └── employeesWithRepeat.txt        ← input file with duplicate names
├── dist/
│   └── EmployeePayroll.jar            ← compiled runnable JAR
├── out/                               ← compiled .class files (auto-generated)
├── src/
│   └── com/
│       └── employeepayroll/
│           ├── models/
│           │   └── Employee.java
│           ├── services/
│           │   └── EmployeeService.java
│           ├── sorting/
│           │   ├── SelectionSort.java
│           │   ├── QuickSort.java
│           │   ├── BinarySearch.java
│           │   └── EmployeeComparable.java
│           ├── utils/
│           │   ├── WriteToFile.java
│           │   └── PerformanceTester.java
│           └── Main.java
└── MANIFEST.MF
```

---

## Requirements

- Java JDK 8 or higher
- No external dependencies

Check your Java version:
```bash
java -version
```

---

## How to Run

### Option 1 — Run the JAR directly (recommended)

1. Unzip the project folder
2. Open a terminal at the **root of the project** (where `data/` and `dist/` are visible)
3. Run:

```bash
java -jar dist/EmployeePayroll.jar
```

### Option 2 — Recompile and run from source

```bash
# 1. Compile all source files
javac -d out -sourcepath src src/com/employeepayroll/Main.java

# 2. Copy data folder into out/
cp -r data out/

# 3. Rebuild the JAR
jar cfm dist/EmployeePayroll.jar MANIFEST.MF -C out .

# 4. Run
java -jar dist/EmployeePayroll.jar
```

---

## Input File Format

Each line in the input file follows this format:

```
id,name,hoursWorked,hourlyRate,deductionProvince,deductionFederal,educationAllowance
```

Example:
```
1,Gemma,37.03,29.30,73.83,80.86,42.18
2,Kelsey,54.26,53.15,41.65,65.80,35.58
```

- Delimiter: `,` (comma)
- Deductions: fixed dollar amounts
- Blank lines and lines starting with `#` are ignored

---

## Features

| Option | Feature |
|--------|---------|
| 1 | Print all employees |
| 2 | Sort by salary using **SelectionSort** → writes `sortedemployeeBySalary.csv` |
| 3 | Sort by name using **QuickSort** → writes `sortedemployeeByName.csv` |
| 4 | Compare **SelectionSort vs QuickSort** performance in ms |
| 5 | **Binary search** by employee name |
| 0 | Exit |

---

## Output Files

After sorting, two CSV files are generated in the `data/` folder:

| File | Description |
|------|-------------|
| `data/sortedemployeeBySalary.csv` | Employees sorted by net hourly salary (low → high) |
| `data/sortedemployeeByName.csv` | Employees sorted by name (A → Z) |

Both files use the same format as the input file.

---

## Net Hourly Salary Formula

```
grossPay      = hoursWorked × hourlyRate
netPay        = grossPay − deductionProvince − deductionFederal + educationAllowance
netHourlyRate = netPay / hoursWorked
```

---

## Important Notes

- Run the JAR from the **project root directory** so it can find the `data/` folder
- Sort by name (Option 4) must be run **before** using Binary Search (Option 6)
- Output CSV files will appear in the `data/` folder after sorting