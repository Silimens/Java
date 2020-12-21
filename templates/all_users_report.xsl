<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="1.0">
    <xsl:output method="html"/>

    <xsl:template match="/">
        <html>
            <body>
                <h1>Полный отчет</h1>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="/REPORT/USERS/USER">
        <div>
            <h2><xsl:value-of select="attribute::fullName"/></h2>
            <span><xsl:value-of select="attribute::fullName"/> потратил <xsl:value-of select="attribute::hours"/>:<xsl:value-of select="attribute::minutes"/>:<xsl:value-of select="attribute::seconds"/> на следующие задачи.</span>
            <xsl:apply-templates/>
        </div>
    </xsl:template>

    <xsl:template match="/REPORT/USERS/USER/TASKS">
        <div>
            <h3>Задачи</h3>
            <div>
                <xsl:apply-templates/>
            </div>
        </div>
    </xsl:template>

    <xsl:template match="/REPORT/USERS/USER/TASKS/TASK">
        <h4><xsl:value-of select="attribute::name"/></h4>
        <span><xsl:value-of select="../../attribute::fullName"/> потратил <xsl:value-of select="attribute::hours"/>:<xsl:value-of select="attribute::minutes"/>:<xsl:value-of select="attribute::seconds"/> на задачу "<xsl:value-of select="attribute::name"/>"</span>
        <table>
            <xsl:apply-templates/>
        </table>
    </xsl:template>

    <xsl:template match="/REPORT/USERS/USER/TASKS/TASK/TRACKS/TRACK">
        <tr>
            <td><xsl:value-of select="attribute::begin"/></td>
            <td><xsl:value-of select="attribute::end"/></td>
            <td><xsl:value-of select="attribute::comment"/></td>
        </tr>
    </xsl:template>

</xsl:stylesheet>