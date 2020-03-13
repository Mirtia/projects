using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class RotatingPlane : MonoBehaviour {

    public float rotatingSpeed = 20f;
	// Use this for initialization
	void Start () {
       
    }
	
	// Update is called once per frame
	void Update () {
        transform.Rotate(0, rotatingSpeed * Time.deltaTime, 0);
    }
}
