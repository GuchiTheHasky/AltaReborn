import {FC} from "react";

interface TaskContent {
    value: string;
}
export const ImageRender: FC<TaskContent> = ({value: link}) => {
    const imagePath = `${link}`;
    return <img src={imagePath} alt="Image"/>;
};