export NODE_ENV=production
npx react-router build

rm -rf ../ejem36_build_folder_backend/java/src/main/resources/static/spa/* 
mkdir -p ../ejem36_build_folder_backend/java/src/main/resources/static/spa/
cp -r ./build/client/* ../ejem36_build_folder_backend/java/src/main/resources/static/spa/

rm -rf ../ejem36_build_folder_backend/node/public/spa/*
mkdir -p ../ejem36_build_folder_backend/node/public/spa/
cp -r ./build/client/* ../ejem36_build_folder_backend/node/public/spa/