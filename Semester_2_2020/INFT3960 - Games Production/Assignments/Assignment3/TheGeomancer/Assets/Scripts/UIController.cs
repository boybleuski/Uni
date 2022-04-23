using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class UIController : MonoBehaviour
{
    OscarController player;

    // Sprites used for icons - connected within Unity
    public Sprite fire;
    public Sprite noFire;
    public Sprite water;
    public Sprite noWater;

    void Start()
    {
        // Find the play character
        player = GameObject.FindGameObjectWithTag("Player").GetComponent<OscarController>();
    }

    // Update is called once per frame
    void Update()
    {
        Image fireIcon = GameObject.Find("fire").GetComponent<Image>();
        Image waterIcon = GameObject.Find("water").GetComponent<Image>();

        // Show a fire icon is the PC can use fire, greyed out if not
        if (player.hasFire)
            fireIcon.sprite = fire;
        else
            fireIcon.sprite = noFire;

        // Show a water icon is the PC can use fire, greyed out if not
        if (player.hasWater)
            waterIcon.sprite = water;
        else
            waterIcon.sprite = noWater;
    }
}
