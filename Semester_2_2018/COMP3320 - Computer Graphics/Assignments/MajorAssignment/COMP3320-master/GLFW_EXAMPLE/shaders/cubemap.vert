#version 150

in vec3 position;

uniform mat4 view;
uniform mat4 proj;

out vec3 Texcoord;

void main()
{
	vec4 pos = proj * view * vec4(position, 1.0f);

	Texcoord = position;
	
    gl_Position = pos.xyww;
}

