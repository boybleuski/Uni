#version 150

in vec3 Texcoord;

out vec4 outColor;

uniform samplerCube skybox;

void main()
{	
	outColor = texture(skybox, Texcoord);
}

