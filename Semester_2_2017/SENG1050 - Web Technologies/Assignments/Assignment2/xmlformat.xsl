<!--
File: xmlformat.xsl
Name: Sam Dolbel
Student no.: c3130069
Date created: 18-10-2017
Date modified: 20-10-2017
-->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html" />

  <xsl:template match="restaurants">
    <html>
      <head>
        <title><xsl:value-of select="restaurant/cuisine" /><xsl:text> Cuisine</xsl:text></title>
      </head>
      <body style="background-image:url('images/farm-3.jpg'); background-repeat:no-repeat; background-position:center top;
    	background-size:100%; background-attachment:fixed;margin-left:1cm;font-family:verdana,serif;">
        <h1 style="font-family:georgia,serif; color:white; text-align:center; text-decoration:underline; text-transform:uppercase;">
        <xsl:value-of select="restaurant/cuisine" /><xsl:text> Cuisine</xsl:text></h1>

        <xsl:for-each select="restaurant">
          <xsl:sort select="restname"/>
          <h2>
            <xsl:value-of select="restname" />
          </h2>
          <p>
            <xsl:value-of select="restlogo" />
          </p>
          <p>
            <xsl:apply-templates select="foodpic"/>
          </p>
          <p>
            <strong>URL: </strong><xsl:value-of select="url" />
          </p>
          <p>
            <strong>Address: </strong><xsl:value-of select="address" />
          </p>
          <p>
            <strong>Phone: </strong><xsl:value-of select="phone" />
          </p>
          <p><strong>Open: </strong>
            <xsl:value-of select="openhours/time" />
            <xsl:text>, </xsl:text>
            <xsl:value-of select="openhours/days" />
          </p>
          <p><strong>Minimum delivery: </strong>
            <xsl:value-of select="delivery/minprice" />
            <xsl:text>, + delivery fee </xsl:text>
            <xsl:value-of select="delivery/deliveryfee" />
          </p>
          <p><strong>Description: </strong>
            <xsl:value-of select="description" />
          </p>
          <p><strong>Head chef: </strong>
            <xsl:value-of select="headchef" />
          </p>
          <p><strong>Current special/s: </strong>
            <xsl:value-of select="specials/specialitem" />
          </p>
          <p><strong>Price range: </strong>
            <xsl:value-of select="pricerange/lowprice" />
            <xsl:text>-</xsl:text>
            <xsl:value-of select="pricerange/highprice" />
          </p>
        </xsl:for-each>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="foodpic">
    <td><img src="{.}" height="100" width="100"></img></td>
  </xsl:template>
</xsl:stylesheet>
