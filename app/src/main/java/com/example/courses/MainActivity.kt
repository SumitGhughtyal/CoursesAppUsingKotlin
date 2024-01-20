package com.example.courses


import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.Data.DataSource
import com.example.courses.ui.theme.CoursesTheme
import com.example.courses.model.Topic

import kotlinx.coroutines.handleCoroutineException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TopicGrid(
                        modifier = Modifier.padding(dimensionResource(R.dimen.padding_small))
                    )

                }
            }
        }
    }
}


@Composable
fun TopicGrid(modifier: Modifier=Modifier)
{
    LazyVerticalGrid(columns = GridCells.Fixed(2),
         verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
         horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding_small)),
        modifier = modifier
    )
    {
        items(DataSource.topics){topic->
            TopicCard(topic)

        }
    }

}

@Composable
fun TopicCard(topic:Topic, modifier: Modifier= Modifier)
{
    Card {
        Row {
            Box{
                Image(
                    painter = painterResource(id = topic.imageRes) ,
                    contentDescription = null,
                    modifier=modifier
                        .size(width=68.dp, height = 68.dp )
                    )
            }

            Column {
                Text(
                    text = stringResource(id = topic.name),
                    style=MaterialTheme.typography.bodyMedium,
                    modifier=Modifier
                        .padding(
                            start= dimensionResource(id = R.dimen.padding_medium),
                            top=dimensionResource(id = R.dimen.padding_medium),
                            end=dimensionResource(id = R.dimen.padding_medium),
                            bottom = dimensionResource(id = R.dimen.padding_small)
                        )
                )

                Row (
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = null,
                        modifier=Modifier
                            .padding(start= dimensionResource(id = R.dimen.padding_medium))
                    )

                    Text(
                        text=topic.availableCourses.toString(),
                        style=MaterialTheme.typography.labelMedium,
                        modifier=Modifier.padding(start= dimensionResource(id = R.dimen.padding_small))
                    )

                }
            }
        }
    }

}



@Preview(showBackground = true)
@Composable
fun TopicPreview()
{
    CoursesTheme {
        val topic=Topic(R.string.photography, 321,R.drawable.photography)
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TopicCard(topic=topic)
        }

    }
}

