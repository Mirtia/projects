using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MotionPlane : MonoBehaviour {
       
    public Transform positionBegin;
    public Transform positionFinal;
    private Vector3 newPosition;
    public string currentState;
    public float smoothMotion;
    public float resetTime;

    // Use this for initialization
    void Start()
    {
        ChangeTarget();
    }

    // Update is called once per frame
    void FixedUpdate()
    {
        transform.position = Vector3.Lerp(transform.position, newPosition, smoothMotion * Time.deltaTime);
    }

    void ChangeTarget()
    {
        if (currentState == "Begin")
        {
            currentState = "End";
            newPosition = positionFinal.position;
        }
        else if (currentState == "End")
        {
            currentState = "Begin";
            newPosition = positionBegin.position;
        }
        else if (currentState == "")
        {
            currentState = "End";
            newPosition = positionFinal.position;
        }
        Invoke("ChangeTarget", resetTime);
    }
}




