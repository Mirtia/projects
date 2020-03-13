using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
using System.Text.RegularExpressions;

public class SphereCollision : MonoBehaviour {

    Rigidbody rBody;
    public GameObject lifeOne,lifeTwo,lifeThree;
    public GameObject explosionPrefab;
    private int count_collisions;
    public int maxExplosionParticles;
    public float explosionForce;
    public float explosionRadius;
    public float upwardsModifier;
    private Vector3 respawnPosition;
    // Use this for initialization
    void Start ()
    {        
        count_collisions = 0;
        rBody = GetComponent<Rigidbody>();
        respawnPosition = rBody.position;
    }
	
	// Update is called once per frame
	void Update () {
		
	}

    private void OnCollisionEnter(Collision collision)
    {
        if (collision.gameObject.name == "InvisiblePlane")
        {
            Debug.Log("Out of bounds");
            count_collisions++;
            if (count_collisions > 3)
            {
                Debug.Log("Game Over");
                Invoke("GameOver", 0.5f);
            }    
            switch (count_collisions)
            {
                case 1:
                    lifeThree.SetActive(false);
                    transform.position = respawnPosition;
                    break;
                case 2:
                    lifeTwo.SetActive(false);
                    transform.position = respawnPosition;
                    break;
                case 3:
                    lifeOne.SetActive(false);
                    transform.position = respawnPosition;
                    break;
            }
            
    
            
        }
        if (collision.gameObject.name == "KronosHit")
        {
            
            Debug.Log("Level Complete");
            Explosion(collision);
            Invoke("GameVictory",3.0f);

        }
        
        if (Regex.IsMatch(collision.gameObject.name, "MovingPlane", RegexOptions.IgnoreCase))
        {
                transform.parent = collision.transform;
        }
        
    }

    private void OnCollisionExit(Collision collision)
    {
        if (Regex.IsMatch(collision.gameObject.name, "MovingPlane", RegexOptions.IgnoreCase))
        {
            transform.parent = null;
        }
    }

    private void Explosion(Collision collision)
    {
        gameObject.SetActive(false);
                
        for (int x = 0; x < maxExplosionParticles; x++)
        {
            GameObject p = Instantiate(explosionPrefab, collision.contacts[0].point, Quaternion.identity);
            Rigidbody pr = p.GetComponent<Rigidbody>();
            pr.AddExplosionForce(explosionForce, collision.contacts[0].point, explosionRadius,upwardsModifier);
        }

        

    }

    private void GameVictory()
    {
        SceneManager.LoadScene("GameVictory");

    }
    
    private void GameOver()
    {
        Time.timeScale = 1.0f;
        SceneManager.LoadScene("GameOver");
              
    }
 }
