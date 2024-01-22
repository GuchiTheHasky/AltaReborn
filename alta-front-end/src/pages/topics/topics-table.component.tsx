import {TopicResponse} from "../../api/topics/dto/topics-response.dto.ts";
import {FC} from "react";
import {Checkbox, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@mui/material";

interface TopicsTableProps {
    topics: TopicResponse[];
    selectedTopicIds: number[];
    setSelectedTopicIds: (value: number[]) => void;

}

export const TopicsTable: FC<TopicsTableProps> = ({topics, setSelectedTopicIds, selectedTopicIds}) => {

    const handleClick = (_: any, id: number) => {
        const selectedIndex = selectedTopicIds.indexOf(id);
        let newSelected: number[] = [];

        if (selectedIndex === -1) {
            newSelected = newSelected.concat(selectedTopicIds, id);
        } else if (selectedIndex === 0) {
            newSelected = newSelected.concat(selectedTopicIds.slice(1));
        } else if (selectedIndex === selectedTopicIds.length - 1) {
            newSelected = newSelected.concat(selectedTopicIds.slice(0, -1));
        } else if (selectedIndex > 0) {
            newSelected = newSelected.concat(
                selectedTopicIds.slice(0, selectedIndex),
                selectedTopicIds.slice(selectedIndex + 1),
            );
        }
        setSelectedTopicIds(newSelected);
    };

    const isSelected = (id: number) => selectedTopicIds.indexOf(id) !== -1;
    const TopicCell = ({topic}: { topic: TopicResponse }) => {
        return (<><TableCell padding="checkbox">
            <Checkbox
                color="primary"
                checked={isSelected(topic.id)}
                onClick={(event) => handleClick(event, topic.id)}
            />
        </TableCell>
            <TableCell component="th" scope="row">
                {topic.title}
            </TableCell></>)
    }

    const topicsRowContent = [];
    let index = 0;


    while (index < topics.length) {
        if (index == topics.length - 1) {
            const topic = topics[index];
            topicsRowContent.push(<TableRow
                key={`topic-key-#1-${topic.id}`}
                sx={{'&:last-child td, &:last-child th': {border: 0}, cursor: 'pointer'}}
            >
                <TopicCell topic={topic}/>
            </TableRow>)
        } else {
            const topic = topics[index];
            const nextTopic = topics[index + 1];
            topicsRowContent.push(<TableRow
                key={`topic-key-#1-${topic.id}-#2-${topic.id}`}
                sx={{'&:last-child td, &:last-child th': {border: 0}, cursor: 'pointer'}}
            >
                <TopicCell topic={topic}/>
                <TopicCell topic={nextTopic}/>
            </TableRow>)
        }

        index += 2;
    }


    return <TableContainer>
        <Table sx={{minWidth: 650}} aria-label="simple table">
            <TableHead>
                <TableRow>
                    <TableCell padding="checkbox"/>
                    <TableCell>Тема</TableCell>
                    <TableCell padding="checkbox"/>
                    <TableCell>Тема</TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {topicsRowContent}
            </TableBody>
        </Table>
    </TableContainer>
}
