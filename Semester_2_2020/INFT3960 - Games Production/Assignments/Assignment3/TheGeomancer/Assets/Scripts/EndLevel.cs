using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class EndLevel : MonoBehaviour
{
    private void OnCollisionEnter2D(Collision2D coll)
    {
        if (coll.transform.CompareTag("Player"))
        {
            SceneManager.LoadScene("WinScreen");
        }
    }
}