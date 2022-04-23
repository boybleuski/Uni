using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Fireball : MonoBehaviour
{
    public Transform fire_pos;
    public GameObject firePrefab;
    public OscarController player;

    void Update()
    {
        // only fire if player has Fire power
        if (Input.GetKeyDown(KeyCode.C) && player.hasFire)
        {
            ThrowFireball();
            player.hasFire = false;
        }

        void ThrowFireball()
        {
            GameObject fb = Instantiate(firePrefab, fire_pos.position, fire_pos.rotation);
            // Injects a function call into another script
            fb.SendMessage("Prepare", player.facingRight);
        }
    }
}
