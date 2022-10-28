cmake_minimum_required(VERSION 3.18)

project(app VERSION 0.0.1 LANGUAGES CXX)

find_package(curl REQUIRED CONFIG)

add_library(app SHARED app.cpp)
target_link_libraries(app curl::curl)
