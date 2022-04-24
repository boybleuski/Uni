<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="html"/>

   <xsl:template match="bookings">

	<html>

	   <head>
		<title>Banana Airline Bookings</title>
		<link rel="stylesheet" href="style.css" type="text/css" />
		<style type="text/css">
			p.green {color: green}
			p.red {color: red}
			div.green {color: green}
			div.red {color: red}

			ol.color {color: blue}
		</style>
		
	   </head>

	   <body>
			<h1>Welcome to Banana Airlines</h1>		
			<hr />
			<h2>Booking for </h2>
			<xsl:apply-templates select="booking" />
	   </body>

	</html>

   </xsl:template>

   <xsl:template match="booking">
		<p style="font-style:italic" class="green">
		<xsl:value-of select="passenger/givenname" />
		<xsl:text> </xsl:text>
		<xsl:value-of select="passenger/initial" />
		<xsl:text>. </xsl:text>
		<xsl:value-of select="passenger/familyname" />
		<xsl:text> </xsl:text>
		<br />
		<xsl:if test='address!=""'>
			<xsl:value-of select="address" />
			<xsl:text> </xsl:text>
		</xsl:if>	
		<br />
		<xsl:value-of select="phone" />
		<xsl:text> </xsl:text>
		</p>
		
	<ol class="color">
	<xsl:for-each select="leg">
		<!--<xsl:sort select="date" /> -->
		<!--<xsl:if test='class="Economy"'>-->
		<li><xsl:text>Date: </xsl:text><xsl:value-of select="date" />
	
			<ul>
				<li><xsl:text>Flight: </xsl:text><xsl:value-of select="flight" /></li>
				<li><xsl:if test='depart/airportcode="SYD"'>
						<div class="red">  				
							<xsl:text>Depart Airport: </xsl:text><xsl:value-of select="depart/airportcode/@airport" />
							<xsl:text> [</xsl:text><xsl:value-of select="depart/airportcode" /><xsl:text>]</xsl:text>
						</div>
					</xsl:if>
					<xsl:if test='depart/airportcode!="SYD"'>
						<div class="green">
							<xsl:text>Depart Airport: </xsl:text><xsl:value-of select="depart/airportcode/@airport" />
							<xsl:text> [</xsl:text><xsl:value-of select="depart/airportcode" /><xsl:text>]</xsl:text>
						</div> 
					</xsl:if></li>
				<li><xsl:text>Depart Time: </xsl:text><xsl:value-of select="depart/time" /></li>
				<li><xsl:text>Arrive Airport: </xsl:text><xsl:value-of select="arrive/airportcode/@airport" />
							<xsl:text> [</xsl:text><xsl:value-of select="arrive/airportcode" /><xsl:text>]</xsl:text></li>
				<li><xsl:text>Arrive Time: </xsl:text><xsl:value-of select="arrive/time" /></li>
				<li><xsl:text>Class: </xsl:text><xsl:value-of select="class" /></li>
				<xsl:choose>
					<xsl:when test='aircraft!=""'>
						<li><xsl:text>Aircraft: </xsl:text><xsl:value-of select="aircraft" /></li>
					</xsl:when>
					<xsl:otherwise>
					<li><xsl:text>Plane Type Unknown </xsl:text></li>
					</xsl:otherwise>
				</xsl:choose>
				
			</ul><br />
		</li>
		<!--</xsl:if>-->
	</xsl:for-each>
	</ol>
   </xsl:template>
   
</xsl:stylesheet>