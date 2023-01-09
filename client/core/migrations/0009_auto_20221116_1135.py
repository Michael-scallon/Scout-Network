# Generated by Django 3.2 on 2022-11-16 11:35

from django.db import migrations, models
import django.utils.timezone


class Migration(migrations.Migration):

    dependencies = [
        ('core', '0008_file_title'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='file',
            name='title',
        ),
        migrations.AddField(
            model_name='post',
            name='contentv2',
            field=models.TextField(default=django.utils.timezone.now),
            preserve_default=False,
        ),
    ]
