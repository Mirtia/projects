#version 150 core


#define maxLights 3

uniform  vec3 fView; //camera 
in vec3 fNormal;

out vec4 colorOUT;

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

in LightH lh[maxLights];
in vec3 fPosition;

uniform int currentLights;

uniform LightSource lightSources[maxLights];
uniform Material modelMaterial; 

void main() 
{ 
	vec4 addition = vec4(0.0,0.0,0.0,1.0);
	
	
	vec4  sum = vec4(0.0,0.0,0.0,0.0);
	
	for( int i=0; i<maxLights ; i++ )
	{				
		
		float dist = length(lh[i].lightDirection);
		float attenuation = 1.0 / (lightSources[i].constantAttenuation 
				+ lightSources[i].linearAttenuation * dist + lightSources[i].quadraticAttenuation * (dist * dist));    
		
		//Ambient Light
	
		vec4 ambient = lightSources[i].ambient * modelMaterial.ambient ;
		
		//Diffuse Light

		vec4 diffuse = lightSources[i].intensity * lightSources[i].diffuse 
			* modelMaterial.diffuse * max(0.0,dot(fNormal.xyz ,lh[i].lightDirection))* attenuation; 

		//Specular Light
		
		float sCoe =  pow(max(dot(fNormal.xyz,lh[i].halfwayVector), 0.0),modelMaterial.shininess);
		vec4 specular =  sCoe *lightSources[i].intensity * lightSources[i].specular * modelMaterial.specular * attenuation; 
		
	    	if(dot(lh[i].lightDirection, fNormal) < 0.0)
		  specular = vec4(0.0, 0.0, 0.0, 1.0);   
	
		
		sum = ambient + diffuse + specular ;
		addition += vec4(sum.xyz,1.0);

	}
		
	    colorOUT = vec4(addition.xyz,1.0);
	


}

