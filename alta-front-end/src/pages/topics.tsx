import { GreenButton } from "../components/buttons/green-button.component.tsx";
import { TopicsTable } from "../modules/topics/topics-table.component.tsx";
import { useGetTopics } from "../api/topics/useGetTopics.ts";
import { Backdrop, CircularProgress } from '@mui/material';


export const Topics = () => {
  const { data: topics, isLoading } = useGetTopics();
  return (
    <div>
      {isLoading && (
        <Backdrop open={isLoading}>
          <CircularProgress color="inherit" />
        </Backdrop>
      )}
      <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
        <h2 className="text-green-primary w-[700px]">Теми:</h2>
        <TopicsTable topics={topics} />
        <div className="w-[300px]">
          <GreenButton label="ДАЛІ" />
        </div>
      </div>
    </div>
  );
};



// import { GreenButton } from "../components/buttons/green-button.component.tsx";
// import { TopicsTable } from "../modules/topics/topics-table.component.tsx";
// import { useGetTopics } from "../api/topics/useGetTopics.ts";
// import { Backdrop, CircularProgress } from '@mui/material';
//
// export const Topics = () => {
//   const { data: topics, isLoading } = useGetTopics();
//
//   return (
//       <div>
//         {isLoading && (
//             <Backdrop open={isLoading}>
//               <CircularProgress color="inherit" />
//             </Backdrop>
//         )}
//
//         <div className="flex w-full flex-col items-center justify-center gap-4 pt-8">
//           <h2 className="text-green-primary w-[700px]">Теми:</h2>
//
//           {/* Використовуємо грід для відображення тем в дві колонки */}
//           <div className="grid grid-cols-2 gap-4">
//             {topics && topics.map((topic) => (
//                 <div key={topic.id} className="border p-4">
//                   {/* Ваш код для відображення кожної теми */}
//                   <h3>{topic.title}</h3>
//                   {/* Інші дані теми, які ви хочете відобразити */}
//                 </div>
//             ))}
//           </div>
//
//           <div className="w-[300px]">
//             <GreenButton label="ДАЛІ" />
//           </div>
//         </div>
//       </div>
//   );
// };
