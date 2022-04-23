using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KillPlayer : MonoBehaviour
{
    [SerializeField]
    Transform spawn;

    private void OnCollisionEnter2D(Collision2D coll)
    {
        // Kill/respawn PC - credit to BurgZerg Arcade for efficiency https://www.youtube.com/watch?v=Qn8tJhnY3eI
        if (coll.transform.CompareTag("Player"))
            coll.transform.position = spawn.position;
    }
}
