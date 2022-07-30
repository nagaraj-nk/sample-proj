echo Dependency Check - Process Started
echo Scanning repo: %1
cd repo/
mkdir %2
cd %2
git clone %1
dependency-check -scan %2 --format JSON --prettyPrint