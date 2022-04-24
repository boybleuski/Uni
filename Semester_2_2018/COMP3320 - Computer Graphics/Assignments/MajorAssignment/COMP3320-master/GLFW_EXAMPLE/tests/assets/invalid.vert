#version 150



void main()
{
	//Pass through the texture and colour
	Texcoord = texcoord;
	Colour = colour;
	
	//Send the view space normals for later
	vec4 norm = view * model * vec4(normal, 0.0);
	
	//Make sure the normals are normalised (don't trust the user)
	//Also note that it is better to check this in the vertext shader 
	//as it requires fewer total operations
	fragNormalView = vec4(normalize(norm.xyz),0.0);
	
	//send the view space positions for later
	fragPositionView = view * model * vec4(position, 1.0);

	//send projected position so we know where to draw on screen
    gl_Position = proj * fragPositionView;

    //Remember, everything which is passed through to the fragment shader
    //is interpolated between vertices. This includes Texcoord, Colour,
    //fragNormalView, fragPositionView, and gl_Position
}

