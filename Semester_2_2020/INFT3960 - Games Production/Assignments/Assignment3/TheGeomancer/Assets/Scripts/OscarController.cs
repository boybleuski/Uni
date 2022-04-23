using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class OscarController : MonoBehaviour
{
    // Move player in 2D space
    public float speed = 1f;
    public float jumpForce = 9.0f;

    // Get all relevant objects
    private Rigidbody2D rigid;
    private PolygonCollider2D coll;
    private Animator anim;
    public ParticleSystem waterJet;
    public Transform spawn;

    public bool hasWater;
    public bool hasFire;
    public bool hasWind;
    public bool facingRight = true;
    private bool isGrounded;

    // Use this for initialization
    void Start()
    {
        rigid = GetComponent<Rigidbody2D>();
        coll = GetComponent<PolygonCollider2D>();
        anim = GetComponent<Animator>();
        hasWater = false;
        hasFire = false;
        hasWind = true;
}

    void Update()
    {
        // move/flip PC left
        if (Input.GetKey(KeyCode.LeftArrow))
        {
            transform.position += new Vector3(-0.1f, 0) * speed;
            Flip(1);
            anim.SetBool("isRunning", true);
        }
        // move/flip PC right
        else if (Input.GetKey(KeyCode.RightArrow))
        {
            transform.position += new Vector3(0.1f, 0) * speed;
            Flip(-1);
            anim.SetBool("isRunning", true);
        }
        // make PC idle
        else
        {
            anim.SetBool("isRunning", false);
        }

        // Water Jet
        if (Input.GetKey(KeyCode.DownArrow) && (Input.GetKeyDown(KeyCode.Z)) && hasWater)
        {
            rigid.velocity = Vector2.up * jumpForce * 2;
            Instantiate(waterJet, transform.position, Quaternion.identity);
            anim.SetBool("isJumping", true);
            hasWater = false;
            isGrounded = false;
        }

        // Jump, but no double jumps
        if (Input.GetKeyDown(KeyCode.Space) && isGrounded)
        {
            rigid.velocity = Vector2.up * jumpForce;
            anim.SetBool("isJumping", true);
            isGrounded = false;
        }

        // Air Dash, only once before landing
        if (Input.GetKeyDown(KeyCode.X) && hasWind)
        {
            if (facingRight)
                rigid.velocity = new Vector2(1, 1) * jumpForce;
            else
                rigid.velocity = new Vector2(-1, 1) * jumpForce;

            hasWind = false;
        }

        // Exit to Title Screen
        if (Input.GetKeyDown(KeyCode.Escape))
        {
            SceneManager.LoadScene("TitleScreen");
        }

        // Set/reset airborne animations
        if (!isGrounded)
        {
            if (rigid.velocity.y < 0)
            {
                anim.SetBool("isJumping", false);
                anim.SetBool("isFalling", true);
            }
            else if (rigid.velocity.y == 0)
            {
                isGrounded = true;
                anim.SetBool("isJumping", false);
                anim.SetBool("isFalling", false);
            }
        }

        // Allow Air Dash after landing
        if (isGrounded)
        {
            hasWind = true;
        }
    }

    // Flip the PC sprite so they are facing the right way
    void Flip(float currentVelocity)
    {
        // if velocity < 0, PC is going right.  If velocity > 0, PC is going left
        if (currentVelocity < 0 && !facingRight || currentVelocity > 0 && facingRight)
        {
            facingRight = !facingRight;
            // Flip the PC sprite based on the direction fired - credit to Nick Hwang https://www.youtube.com/watch?v=ccxXxvlS4mI
            Vector3 scale = transform.localScale;
            scale.x *= -1;
            transform.localScale = scale;
        }
    }

    // Refill PC's Fire power
    public void ClaimFire()
    {
        hasWater = false;
        hasFire = true;
    }

    // Refill PC's Water power
    public void ClaimWater()
    {
        hasWater = true;
        hasFire = false;
    }

    // Kill/respawn PC - credit to BurgZerg Arcade for efficiency https://www.youtube.com/watch?v=Qn8tJhnY3eI
    public void Die()
    {
        coll.transform.position = spawn.position;
    }
}