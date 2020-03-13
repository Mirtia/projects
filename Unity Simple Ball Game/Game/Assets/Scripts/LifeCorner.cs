using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LifeCorner : MonoBehaviour {

    public Camera camera;
    public float offset;
    private Vector3 pos;
	// Use this for initialization
	void Start () {
        pos = new Vector3(0.03f + offset, 0.90f, 10.00f );
    }
	
	// Update is called once per frame
	void Update () {
        
        transform.position = camera.ViewportToWorldPoint(pos);
        
        
    }
}
