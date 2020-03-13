#version 150 core

in  vec4 vPosition;
in  vec4 vColor;
out vec4 color;

uniform mat4 transformMatrix;

void main()
{
	//Uncomment if you want to see the basic cube translated.
    gl_Position = transformMatrix*vPosition;

	//gl_Position = vPosition;
    color = vColor;
}
