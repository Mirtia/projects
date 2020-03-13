#version 150 core

in vec4 color;
out  vec4  colorOUT;

uniform vec4 diffuseColor;

void main() 
{ 
    colorOUT = diffuseColor;
    //colorOUT = vec4(0.0,1.0,0.0,1.0);
} 

