npx react-router build

rm -rf ../ejem35_build_backend/java/src/main/resources/static/* 
cp -r ./build/client/* ../ejem35_build_backend/java/src/main/resources/static/

rm -rf ../ejem35_build_backend/node/public/*
cp -r ./build/client/* ../ejem35_build_backend/node/public/