echo Dependency Check - Process Started
echo Scanning repo: %1
cd repo/
mkdir %2
cd %2
git clone %1
cd %2
mvn clean install