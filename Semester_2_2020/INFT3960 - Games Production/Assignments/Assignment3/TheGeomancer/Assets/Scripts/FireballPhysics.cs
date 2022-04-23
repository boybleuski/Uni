using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class FireballPhysics : MonoBehaviour
{
    public float speed = 20f;
    public Rigidbody2D rigid;

    void Prepare(bool facingRight)
    {
        // Set initial velocity - transform.forward is 3D only, credit to Brackey (https://www.youtube.com/watch?v=wkKsl1Mfp5M)
        if (facingRight)
        {
            rigid.velocity = transform.right * speed;
        }
        else
        {
            rigid.velocity = transform.right * -speed;
            // Flip the fireball sprite based on the direction fired - credit to Nick Hwang https://www.youtube.com/watch?v=ccxXxvlS4mI
            Vector3 scale = transform.localScale;
            scale.x *= -1;
            transform.localScale = scale;
        }
    }


    void OnTriggerEnter2D(Collider2D coll)
    {
        // Ignore player when firing
        if (coll.gameObject.tag != "Player")
        {
            Enemy enemy = coll.GetComponent<Enemy>();
            if (enemy != null)
                enemy.Die();

            Destroy(gameObject);
        }
    }
}
