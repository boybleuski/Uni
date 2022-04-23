using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class WaterPool : MonoBehaviour
{
    void OnTriggerEnter2D(Collider2D coll)
    {
        // if player, absorb water power
        if (coll.CompareTag("Player"))
        {
            OscarController player = coll.GetComponent<OscarController>();
            player.ClaimWater();
        }
    }
}
