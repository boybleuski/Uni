using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PollenPhysics : MonoBehaviour
{
    public float speed = 3f;
    public Rigidbody2D rigid;

    void Start()
    {
        // Set initial velocity - transform.forward is 3D only, credit to Brackey (https://www.youtube.com/watch?v=wkKsl1Mfp5M)
        rigid.velocity = transform.right * -speed;
    }


    void OnTriggerEnter2D(Collider2D coll)
    {
        // Prevent the projectile from ever hitting the enemy
        if (!coll.transform.CompareTag("Enemy"))
        {
            if (coll.transform.CompareTag("Player"))
            {
                OscarController player = coll.GetComponent<OscarController>();
                if (player != null)
                    player.Die();
            }

            Destroy(gameObject);
        }
    }
}
