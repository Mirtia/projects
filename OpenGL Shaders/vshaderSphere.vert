#version 150 core


#define maxLights 3


in  vec3 vPosition;
in  vec3 vNormal;
uniform vec3 fView;

out vec3 fNormal;
out vec3 fPosition;


uniform mat4 normalMatrix; 
uniform mat4 modelMatrix; 
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform mat4 modelLightMatrix;

struct LightSource
{ 
   vec4 position; 
   vec4 ambient;              
   vec4 diffuse;             
   vec4 specular;            
   
   float intensity;
   
   float constantAttenuation;  
   float linearAttenuation;   
   float quadraticAttenuation;  
};    

struct Material
{     
   vec4 ambient;     
   vec4 diffuse;     
   vec4 specular;     
   float shininess; 
};  


struct LightH
{
	vec3 lightDirection;
	vec3 halfwayVector;
};

uniform int currentLights;

uniform LightSource lightSources[maxLights];
uniform Material modelMaterial;

out LightH lh[maxLights];


void main()
{
		
		
		for( int i=0 ; i < maxLights; i++)
		{
			fNormal = normalize((modelLightMatrix * vec4(vNormal,1.0)).xyz); 
						
			vec3 fPosition = vec3(( modelMatrix * vec4(vPosition.xyz,1.0)).xyz);
			
		    vec3 lPosition = vec3(( modelLightMatrix * vec4(lightSources[i].position.xyz,1.0)).xyz);
			
			lh[i].halfwayVector = normalize( lPosition + fPosition );
			
			lh[i].lightDirection = normalize(lPosition - fPosition);
					
			
		
		}
	

		
	gl_Position = projectionMatrix * viewMatrix * modelMatrix * vec4(vPosition.xyz,1);
	
    
}