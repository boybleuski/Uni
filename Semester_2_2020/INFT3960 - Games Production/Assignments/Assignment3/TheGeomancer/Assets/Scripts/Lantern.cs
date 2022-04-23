using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Lantern : MonoBehaviour
{
    void OnTriggerEnter2D(Collider2D coll)
    {
        // if player, absorb fire power
        if (coll.CompareTag("Player"))
        {
            OscarController player = coll.GetComponent<OscarController>();
            player.ClaimFire();
        }
    }
}
