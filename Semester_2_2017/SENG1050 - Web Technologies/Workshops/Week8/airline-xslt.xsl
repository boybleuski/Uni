<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" />
  <xsl:template match="bookings">
    <html>
      <head>
        <title>Banana Airlines</title>
      </head>
      <body>
        <h1>Welcome to Banana Airlines</h1>
        <xsl:apply-templates select="booking"/>
      </body>
    </html>
  </xsl:template>

  <xsl:template match="booking">
    Name:
    <span>
      <xsl:value-of select="passenger/givenname" />
      <xsl:text> </xsl:text>
      <xsl:value-of select="passenger/initial" />
      <xsl:text> </xsl:text>
      <xsl:value-of select="passenger/familyname" />
    </span><br />
    Address:
    <span>
      <xsl:value-of select="address" />
    </span><br />
    Phone:
    <span>
      <xsl:value-of select="phone" />
    </span>
  </xsl:template>
</xsl:stylesheet>
