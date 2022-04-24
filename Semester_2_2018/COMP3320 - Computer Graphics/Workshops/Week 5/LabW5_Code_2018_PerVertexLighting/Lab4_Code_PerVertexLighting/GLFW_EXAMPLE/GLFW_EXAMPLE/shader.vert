#version 150

in vec3 position;
in vec3 colour;
in vec3 normal;
in vec2 texcoord;

uniform mat4 model;
uniform mat4 view;
uniform mat4 proj;

uniform vec4 light_position;
uniform vec4 light_colour;

out vec3 Colour;
out vec2 Texcoord;
out vec4 Normal;

void main()
{
	Texcoord = texcoord;
	
	//Compute world space vertex position and normal
	vec4 world_normal = model * vec4(normal, 0.0);
	vec4 world_pos = model * vec4(position, 1.0);

	//Compute the unit vector pointing to the light from the vertex position
	vec3 lightFromSurfaceDirection = normalize(light_position.xyz - world_pos.xyz);

	//Lambertian diffuse lighting equation
	vec4 diffuse = dot(lightFromSurfaceDirection, normalize(world_normal.xyz)) * light_colour; 
	
	//Set the colour based on lighting calculations
	Colour = diffuse.xyz * colour;
	
	//TODO: Try these lines instead of the above to visualise the normals and positions in world space
	//Colour = abs(world_normal.xyz);
	//Colour = normalize(world_pos.xyz);

	//NOTE: the above is a useful technique for debugging shaders - 
	//just make sure the vec4 you output has values between 0 and 1
	
	//Send the clip space vertex position
    gl_Position = proj * view * world_pos;
}

