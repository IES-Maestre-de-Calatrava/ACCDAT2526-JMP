<?xml version="1.0" encoding='ISO-8859-1'?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
 <xsl:template match='/'>
   <html><xsl:apply-templates /></html>
 </xsl:template>
 <xsl:template match='Objetos'>
    <head><title>LISTADO DE Objetos</title></head>
    <body> 
    <h1>LISTA DE Objetos</h1>
    <table border='1'>
    <tr><th>id</th><th>dni</th><th>apellidos</th><th>nombre</th><th>respuestas</th><th>nota</th></tr>
      <xsl:apply-templates select='objeto' />
    </table>
    </body>
 </xsl:template>
 <xsl:template match='objeto'>
   <tr><xsl:apply-templates /></tr>
 </xsl:template>
 <xsl:template match='id|dni|apellidos|nombre|respuestas|nota'>
   <td><xsl:apply-templates /></td>
 </xsl:template>
</xsl:stylesheet>