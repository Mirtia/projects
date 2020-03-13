using System.Collections;
using System.Collections.Generic;
using UnityEngine;


[RequireComponent(typeof(Rigidbody))]
public class SphereMotion : MonoBehaviour {


        
        public float jumpForce = 7.0f;
        public float moveSpeed = 2.0f;
        

        public bool isGrounded = true;
        Rigidbody rBody;
        AudioSource jumpSound;
    
        void Start()
        {
            rBody = GetComponent<Rigidbody>();
            jumpSound = GetComponent<AudioSource>();
            rBody.freezeRotation = true;
            Physics.gravity = new Vector3(0, -12, 0);
        }

        void OnCollisionStay()
        {
            isGrounded = true;
        }

        void Update()
        {
            transform.Translate(Input.GetAxis("Horizontal") * Time.deltaTime * moveSpeed,0,0);
            if (Input.GetKeyDown(KeyCode.Space) && isGrounded)
            {
                jumpSound.Play();
                rBody.velocity = Vector3.up *jumpForce;
                isGrounded = false;
            }

       
           


    }



}
