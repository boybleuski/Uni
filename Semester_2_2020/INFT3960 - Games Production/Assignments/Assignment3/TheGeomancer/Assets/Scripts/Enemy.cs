using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Enemy : MonoBehaviour
{
    public Transform rosePollen;
    public GameObject pollenPrefab;

    void Start()
    {
        // Fire pollen after 2 seconds, then every 1 second
        InvokeRepeating("ShootPollen", 2.0f, 1.0f);
    }

    void ShootPollen()
    {
        GameObject fb = Instantiate(pollenPrefab, rosePollen.position, rosePollen.rotation);
    }

    public void Die()
    {
        Destroy(gameObject);
    }
}