using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class BackgroundMotion : MonoBehaviour {

    private float length;
    private float startPosition;
    public new GameObject camera;
    public float repeatEffect;
	// Use this for initialization
	void Start () {
        startPosition = transform.position.x;
        length = GetComponent<SpriteRenderer>().bounds.size.x;

	}
	
	// Update is called once per frame
	void Update () {

        float temp = camera.transform.position.x * (1 - repeatEffect);
        float distance = camera.transform.position.x * repeatEffect;

        transform.position = new Vector3(startPosition +
            distance, transform.position.y, transform.position.z);

        if (temp > startPosition + length)
            startPosition += length;
        else if (temp < startPosition - length)
            startPosition -= length;
	}
}
